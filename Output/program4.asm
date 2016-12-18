extern _printf

SECTION .data

intFormat: db "%d", 10, 0
SECTION .text

global _WinMain@16

_soma:
push ebp
mov ebp, esp
push dword [ebp+12]
push dword [ebp+8]
pop ebx
pop eax
add eax, ebx
push eax
pop eax
mov esp, ebp
pop ebp
ret
_sub:
push ebp
mov ebp, esp
push dword [ebp+12]
push dword [ebp+8]
pop ebx
pop eax
sub eax, ebx
push eax
pop eax
mov esp, ebp
pop ebp
ret
_mult:
push ebp
mov ebp, esp
push dword [ebp+12]
push dword [ebp+8]
pop ebx
pop eax
imul eax, ebx
push eax
pop eax
mov esp, ebp
pop ebp
ret
_WinMain@16:
push ebp
mov ebp, esp
sub esp, 4
sub esp, 4
sub esp, 4
push dword 3
pop dword [ebp-4]
push dword 2
pop dword [ebp-8]
push dword [ebp-4]
push dword [ebp-8]
call _soma
add esp, 8
push eax
pop dword [ebp-12]
push dword [ebp-12]
push dword intFormat
call _printf
add esp, 8
push dword [ebp-4]
push dword [ebp-8]
call _sub
add esp, 8
push eax
pop dword [ebp-12]
push dword [ebp-12]
push dword intFormat
call _printf
add esp, 8
push dword [ebp-4]
push dword [ebp-8]
call _mult
add esp, 8
push eax
pop dword [ebp-12]
push dword [ebp-12]
push dword intFormat
call _printf
add esp, 8
mov esp, ebp
pop ebp
ret


