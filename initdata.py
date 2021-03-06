#!/usr/bin/python
# Potřebuje python v3 a knihovnu py-postgresql: http://python.projects.postgresql.org/

import postgresql
import postgresql.exceptions
import sys
import re
import random
from datetime import *
		
# připojení k db, heslo je předáno programu jako 1. parametr
password = sys.argv[1]
CONNECTSTRING = "pq://student_db12_10:{password}@krizik.felk.cvut.cz:5434/student_db12_10".format(password=password)
connection = postgresql.open(CONNECTSTRING)

# vygeneruje náhodné slovo
def randomWord(minlen, maxlen,alphabet="abcdefghijklmnopqrstuvwxyz"):
	alphabet = list(alphabet)
	w = ""
	for x in range(minlen):
		w+= random.choice(alphabet)
	for x in range(random.randint(0,maxlen-minlen)):
		w+= random.choice(alphabet)
	return w

def randomEmail():
	return randomWord(3,7)+"@"+randomWord(3,7)+"."+randomWord(2,3)

# vygeneruje náhodné datum
def randomTimestamp(d=365):
	return datetime.now()-timedelta(days=random.randint(0,d), seconds=random.randint(0,60*60*24-1))

# pomocné funkce
def addRow(fun, r):
	try:
		fun(*r)
	except postgresql.exceptions.UniqueError:
		pass
def getIDs(table):
	return next(zip(*connection.prepare("SELECT id FROM "+table).declare().read()))

# připojení k databázi
with open("schema.sql") as sfile:
	schemacmd = sfile.read()
	if input("Vyprázdnit tabluky? [a/N] ") in ("A", "a"):
		tables = re.findall("CREATE\W+TABLE\W+(\w+)", schemacmd, re.I)
		tables.reverse()
		# vymazání tabulek:
		for table in tables:
			try:
				connection.execute("DROP TABLE "+table+";")
			except postgresql.exceptions.UndefinedTableError:
				pass
		connection.execute(schemacmd)

channel = connection.prepare("INSERT INTO channels (name, description_cs, rating, private_code, private, canonical_name, username, password, role, first_name, last_name, email, phone, purpose) VALUES ($1,$2,$3,$4,$5,$6,$7,$8,$9,$10,$11,$12,$13,$14)")
def randomChannel(): # vygeneruje náhodný kanál
	addRow(channel, (
			randomWord(5,20), # name
			" ".join([randomWord(5, 10) for x in range(random.randint(4,8))]), # description_cs
			random.randint(0,10), # rating
			randomWord(4,10), # private_code
			random.choice([True] + [False for x in range(10)]), # private
			randomWord(5,20), # canonical_name
			randomWord(5,20), # username
			randomWord(32,32,"12334567890abcdef"), # password
			randomWord(5,20), # role
			randomWord(5,20), # first_name
			randomWord(5,20), # last_name
			randomEmail(), #email
			randomWord(9,12,"1234567890"),#phone
			" ".join([randomWord(5, 10) for x in range(random.randint(4,8))]), # purpose
	))

folder = connection.prepare("INSERT INTO folders (name,channel_id) VALUES ($1,$2)")
def randomFolder(channels): # vygeneruje náhodnou složku
	addRow(folder, (randomWord(5,20), random.choice(channels)))
	
speaker = connection.prepare("INSERT INTO speakers (name) VALUES ($1)")
def randomSpeaker(): # vygeneruje náhodného speakera
	addRow(speaker, (randomWord(5,20),))

presentation = connection.prepare("INSERT INTO presentations (title, description, lang, date_recorded, service, service_id, length, slides, video, folder_id) VALUES ($1,$2,$3,$4,$5,$6,$7,$8,$9,$10)")
def randomPresentation(folders):
	addRow(presentation, (
			randomWord(5,20), # title
			" ".join([randomWord(5, 10) for x in range(random.randint(4,8))]), # description
			random.choice(("cs", "en")), # lang
			randomTimestamp(), # date_recorded
			random.choice(("local","local","local","youtube","vimeo")),
			randomWord(5,20), # service_id
			random.randint(5,4*60), # length
			random.choice((True,False)), # slides
			random.choice((True,False)), # video
			random.choice(folders)))

statistic = connection.prepare("INSERT INTO statistics (presentation_id, user_id, loads_count, finishes_count, plays_count) VALUES ($1, $2, $3, $4, $5)")
def randomStatistic(presentations, users):
	lc = random.randint(1, 15)
	pc = random.randint(1, lc)
	fc = random.randint(0, pc)
	addRow(statistic, (random.choice(presentations), random.choice(users), lc, fc, pc))

subscription = connection.prepare("INSERT INTO subscriptions (channel_id, email) VALUES ($1, $2)")
def randomSubscription(channels):
	addRow(subscription, (random.choice(channels), randomEmail()))
	
tag = connection.prepare("INSERT INTO tags (name) VALUES ($1)")
def randomTag(): # vygeneruje náhodného taga
	addRow(tag, (randomWord(5,20),))

tag_presentation = connection.prepare("INSERT INTO tags_presentations (tag_id, presentation_id) VALUES ($1, $2)")
def randomTP(tags, presentations):
	addRow(tag_presentation, (random.choice(tags), random.choice(presentations)))

presentations_speakers = connection.prepare("INSERT INTO presentations_speakers (presentation_id, speaker_id) VALUES ($1, $2)")
def randomPS(spkrs, prsnttns):
	addRow(presentations_speakers, (random.choice(prsnttns), random.choice(spkrs)))

# počty jednotlivých záznamů v db
channelsN = 48
speakersN = 48
foldersN = 48
presentationsN = foldersN*6
subscriptionsN = 25
statisticsN = channelsN*presentationsN//5
tagsN = 64
tags_presentationsN = presentationsN*2
presentations_speakersN = speakersN*2

# uložení záznamů do db
if __name__ == "__main__":
	print("Adding channels")
	for x in range(channelsN):
		randomChannel()
	print("Adding speakers")
	for x in range(speakersN):
		randomSpeaker()
	channelids = getIDs("channels")
	print("Adding folders")
	for x in range(foldersN):
		randomFolder(channelids)
	foldersids = getIDs("folders")
	print("Adding presentations")

	# Custom
	addRow(presentation, ("Introduction to string theory", "A comprehensive lecture on the foundations of modern string theory. Aimed at non prefessional audience.",
			"en", randomTimestamp(), "local", None, random.randint(15,4*60), random.choice((True,False)), random.choice((True,False)), random.choice(foldersids)))
	addRow(presentation, ("Consequences of findings in universal algebra to group theory", "This presentation explains some newly discovered theorems in abstract algebra. Listeners are expected to have solid theorethical background.",
			"en", randomTimestamp(), "local", None, random.randint(15,4*60), random.choice((True,False)), random.choice((True,False)), random.choice(foldersids)))
	addRow(presentation, ("Global warming discussion", "A video record of a discussion about global warming. Possible causes, consequences etc.",
			"en", randomTimestamp(), "local", None, random.randint(15,4*60), random.choice((True,False)), random.choice((True,False)), random.choice(foldersids)))
	addRow(presentation, ("ILP & AI", "A lecture about inductive logic programming and it's uses in artificial inteligence, especially machine learning.",
			"en", randomTimestamp(), "local", None, random.randint(15,4*60), random.choice((True,False)), random.choice((True,False)), random.choice(foldersids)))
	addRow(presentation, ("Economical crisis", "This video features famous experts discussing the current global economical crisis.",
			"en", randomTimestamp(), "local", None, random.randint(15,4*60), random.choice((True,False)), random.choice((True,False)), random.choice(foldersids)))
	addRow(presentation, ("Vernissage in Prague", "A commented walk through a newly opened art gallery in the Prague Castle.",
			"en", randomTimestamp(), "local", None, random.randint(15,4*60), random.choice((True,False)), random.choice((True,False)), random.choice(foldersids)))
	addRow(presentation, ("Remarkable botany", "In this video, you'll learn some interesting facts about the lives of plants. This presentation also icludes never before seen photos from Amazon.",
			"en", randomTimestamp(), "local", None, random.randint(15,4*60), random.choice((True,False)), random.choice((True,False)), random.choice(foldersids)))
	addRow(presentation, ("Life on Moon", "Most people think there was never life on Moon. But can we be really sure? Watch this presentation to find out!",
			"en", randomTimestamp(), "local", None, random.randint(15,4*60), random.choice((True,False)), random.choice((True,False)), random.choice(foldersids)))
	addRow(presentation, ("Nanotechnologies in medicine", "New discoveries and ongoing nanotechnology research with possible future uses in medicine",
			"en", randomTimestamp(), "local", None, random.randint(15,4*60), random.choice((True,False)), random.choice((True,False)), random.choice(foldersids)))
	addRow(presentation, ("Enterprise Java", "Management and programming of big projects in Java. Presenting libraries you shouldn't overlook and a discusstion about current trends and aproaches to project administration.",
			"en", randomTimestamp(), "local", None, random.randint(15,4*60), random.choice((True,False)), random.choice((True,False)), random.choice(foldersids)))


	for x in range(presentationsN-10):
		randomPresentation(foldersids)

	connection.execute("UPDATE presentations SET service_id=NULL WHERE service='local';")
	print("Adding subscriptions")
	for x in range(subscriptionsN):
		randomSubscription(channelids)
	presentationids = getIDs("presentations")
	print("Adding statistics")
	for x in range(statisticsN):
		randomStatistic(presentationids, channelids)
	print("Adding tags")
	for x in range(tagsN):
		randomTag()
	tagids = getIDs("tags")
	print("Adding tags_presentations")
	for x in range(tags_presentationsN):
		randomTP(tagids, presentationids)
	print("Adding presentations_speakers")
	speakerids = getIDs("speakers")
	for x in range(presentations_speakersN):
		randomPS(speakerids, presentationids)
