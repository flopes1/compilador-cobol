extern printf

SECTION .data

a : dd 0
a : dd 1
a : dd 2

SECTION .text

global WinMain@16

push 5
pop [a]
pop [b]


