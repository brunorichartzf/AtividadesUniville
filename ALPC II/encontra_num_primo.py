num = int(input('Primos até: '))
primos = []

for i in range(num + 1):
    primos.append(True)

primos[0] = False
primos[1] = False

max = int(num ** 0.5) + 1
print(max)

for i in range(0, max + 1):
    if primos[i]:
        for j in range(i * 2, num + 1, i):
            primos[j] = False

for i in range(num + 1):
    if primos[i]:
        print(i, end=', ')