#Page Replacement 
def fifo(pagestring, fsize):
	ptr=0
	hit=0
	fault=0
	memory =[ -1 for i in range(fsize)]
	for i in pagestring:
		flag=0
		for j in memory:
			if j==int(i):
				hit+=1
				flag=1
				break
		if flag==0:
			fault+=1
			memory[ptr]=int(i)
			ptr+=1
			if ptr==fsize:
				ptr=0
		print(memory)
	print("No of hits in fifo: ",hit)
	print("No of faults in fifo: ",fault)

	faultratio = fault/len(pagestring) * 100
	hitratio = hit/len(pagestring) * 100
	print("Fault Ratio in fifo: ",faultratio)
	print("Hit Ratio in fifo: ",hitratio)

def lru(pagestring, fsize):
	ptr=0
	hit=0
	fault=0
	memory =[ -1 for i in range(fsize)]
	for i in range(len(pagestring)):
		flag=0
		for j in memory:
			if j==int(pagestring[i]):
				hit+=1
				flag=1
				break
		if flag==0:
			fault+=1
			if ptr>=fsize:
				left = pagestring[0:i]
				dist = [-1 for i in range(10)]
				present = [-1 for i in range(10)]
				for j in range(len(left)):
					k=int(left[j])
					dist[k]=i-j
					for l in range(len(memory)):
						if memory[l]==int(left[j]):
							present[k] = l
							break
				max = 0
				position=0
				for j in range(len(dist)):
					if(dist[j]>max and present[j]>-1):
						max=dist[j]
						position=present[j]
				memory[position]=int(pagestring[i])
			else:
				memory[ptr]=int(pagestring[i])
				ptr+=1
		print(memory)
	print("No of hits in lru: ",hit)
	print("No of faults in lru: ",fault)
	faultratio = fault/len(pagestring) * 100
	hitratio = hit/len(pagestring) * 100
	print("Fault Ratio in lru: ",faultratio)
	print("Hit Ratio in lru: ",hitratio)
	
def opt(pagestring, fsize):
	ptr=0
	hit=0
	fault=0
	memory =[ -1 for i in range(fsize)]
	for i in range(len(pagestring)):
		flag=0
		for j in memory:
			if j==int(pagestring[i]):
				hit+=1
				flag=1
				break
		if flag==0:
			fault+=1
			farthest = -1
			farthest_idx = -1
			if ptr>=fsize:
				for j in range(fsize):
					found = False
					for k in range(i + 1, len(pagestring)):
						if memory[j] == int(pagestring[k]):
							found = True
							if k > farthest:
								farthest = k
								farthest_idx = j
							break
					if not found:
						farthest_idx = j
						break
				memory[farthest_idx] = int(pagestring[i])
			else:
				memory[ptr]=int(pagestring[i])
				ptr+=1
		print(memory)
	print("No of hits in opt: ",hit)
	print("No of faults in opt: ",fault)
	faultratio = fault/len(pagestring) * 100
	hitratio = hit/len(pagestring) * 100
	print("Fault Ratio in opt: ",faultratio)
	print("Hit Ratio in opt: ",hitratio)
		
def main():
	x = input("Enter page string: ")
	f = int(input("Enter frame size: "))
	while(True):
		choice = int(input("1.FIFO \n2.LRU \n3.Optimal \n4.Exit\nYour Choice: "))
		if choice==1:
			fifo(x,f)
		elif choice==2:
			lru(x,f)
		elif choice==3:
			opt(x,f)
		else:
			exit(0)
			
main()	
