import os
f = open("a.txt", "w")
for x in range(1,4400):
	name="ReadsClust"+str(x)
	file=open(name,'r')
	lines=file.readlines()
	if len(lines)==2:
		for y in lines:
			f.write(y)
			pass
		file.close
		print ("del:"+name)
		if os.path.isfile(name):
			os.remove(name)
		pass
	pass
f.close()