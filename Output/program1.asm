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
push dword [ebp-4]
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
push dword [ebp-16]
push dword intFormat
call _printf
add esp, 8
mov esp, ebp
pop ebp
ret


