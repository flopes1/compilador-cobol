extern _printf

SECTION .data

globalIdBool:dd 0
globalIdInt:dd 0
intFormat: db "%d", 10, 0
SECTION .text

global WinMain@16

_WinMain@16:
push ebp
mov ebp, esp
sub esp, 4
sub esp, 4
sub esp, 4
push dword [ebp+8]
push dword 10
pop ebx
pop eax
add eax, ebx
push eax
pop dword [ebp-12]
push dword 5
push dword 9
pop ebx
pop eax
cmp eax, ebx
push dword 1
jmp MAIN_end_cmp_1
MAIN_false_cmp_1:
push dword 0
MAIN_end_cmp_1:
push dword intFormat
call _printf
add esp, 8
mov esp, ebp
pop ebp
ret
_function:
push ebp
mov ebp, esp
sub esp, 4
sub esp, 4
push dword [ebp-8]
push dword intFormat
call _printf
add esp, 8
mov esp, ebp
pop ebp
ret


