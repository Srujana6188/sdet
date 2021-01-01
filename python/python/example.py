"""
a=0
while (a<5) :
    a+=1
    if a==3 :
        continue
    print(a)
fruits = ["apple" , "banana" , "cherry"]
for fruit in fruits :
    if fruit=='banana':
        break
    print(fruit)

for x in range(0,6,2):
    print(x)
else:
    print("finished")
"""
a = [1,2,3]
b = a.append(4)
print(a)
print(b)