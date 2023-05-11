"""Faça um programa que:

Leia um vetor A com N elementos já ordenados e um vetor B com M elementos também já ordenados.
Intercale os dois vetores A e B, formando um vetor C, sendo que ao final do processo de intercalação, 
o vetor C continue ordenado. Nenhum outro processo de ordenação poderá ser utilizado além da intercalação 
dos vetores A e B. Caso um vetor (A ou B) termine antes do outro, o vetor C deverá ser preenchido 
com os elementos do vetor que ainda possui informações."""


A = [1,2,5,6,7,10,11,12,13]
B = [3,4,8,9,14,15]
C = []

a = 0
b = 0

while a < len(A) or b < len(B):
    if a < len(A) and b < len(B):
        if A[a] < B[b]:
            C.append(A[a])
            a += 1
        else:
            C.append(B[b])
            b += 1
    elif a < len(A):
        C.append(A[a])
        a += 1
    else:
        C.append(B[b])
        b += 1

print(C)