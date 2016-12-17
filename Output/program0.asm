extern _printf

SECTION .data

globalIdInt :dd 0
intFormat: db "%d", 10, 0
SECTION .text

global _WinMain@16

_WinMain@16:
push ebp
mov ebp, esp
sub esp, 4
sub esp, 4
push dword 5
pop dword [globalIdInt]
push dword 3
pop dword [ebp-8]
push dword [globalIdInt]
push dword [ebp-8]
pop ebx
pop eax
cmp eax, ebx
jle _false_cmp_MAIN1
push dword 1
jmp _end_cmp_MAIN1
_false_cmp_MAIN1:
push dword 0
_end_cmp_MAIN1:
push dword 1
pop ebx
pop eax
cmp eax, ebx
jne _end_if_MAIN1
push dword 1
pop dword [ebp-4]
jmp _end_else_MAIN1
_end_if_MAIN1:
jmp _else_MAIN1
_else_MAIN1:
push dword 0
pop dword [ebp-4]
_end_else_MAIN1:
push dword [ebp-4]
push dword intFormat
call _printf
add esp, 8
mov esp, ebp
pop ebp
ret


