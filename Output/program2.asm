extern _printf

SECTION .data

intFormat: db "%d", 10, 0
SECTION .text

global _WinMain@16

_WinMain@16:
push ebp
mov ebp, esp
sub esp, 4
sub esp, 4
push dword 0
pop dword [ebp-8]
push dword 5
pop dword [ebp-4]
_while_MAIN1:
push dword [ebp-8]
push dword [ebp-4]
pop ebx
pop eax
cmp eax, ebx
jg _false_cmp_MAIN1
push dword 1
jmp _end_cmp_MAIN1
_false_cmp_MAIN1:
push dword 0
_end_cmp_MAIN1:
push dword 1
pop ebx
pop eax
cmp eax, ebx
jne _end_while_MAIN1
push dword [ebp-8]
push dword 3
pop ebx
pop eax
cmp eax, ebx
jne _false_cmp_MAIN2
push dword 1
jmp _end_cmp_MAIN2
_false_cmp_MAIN2:
push dword 0
_end_cmp_MAIN2:
push dword 1
pop ebx
pop eax
cmp eax, ebx
jne _end_if_MAIN1
jmp _end_while_MAIN1
_end_if_MAIN1:
push dword [ebp-8]
push dword intFormat
call _printf
add esp, 8
push dword [ebp-8]
push dword 1
pop ebx
pop eax
add eax, ebx
push eax
pop dword [ebp-8]
jmp _while_MAIN1
_end_while_MAIN1:
mov esp, ebp
pop ebp
ret


