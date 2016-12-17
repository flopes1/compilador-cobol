extern _printf

SECTION .data

intFormat: db "%d", 10, 0
SECTION .text

global WinMain@16

_mult10:
push ebp
mov ebp, esp
pop eax
mov esp, ebp
pop ebp
ret
_WinMain@16:
push ebp
mov ebp, esp
sub esp, 4
push dword 10
push dword 0
push dword 1
pop ebx
pop eax
cmp eax, ebx
jne _end_if_MAIN1
push dword [ebp-4]
call _mult10
add esp, 4
_end_if_MAIN1:
push dword [ebp-4]
push dword intFormat
call _printf
add esp, 8
mov esp, ebp
pop ebp
ret


