extern _printf

SECTION .data

intFormat: db "%d", 10, 0
SECTION .text

global WinMain@16

_nova:
push ebp
mov ebp, esp
sub esp, 4
push dword [ebp+8]
push dword 1
pop ebx
pop eax
add eax, ebx
push eax
pop dword [ebp-4]
push dword [ebp-4]
push dword 20
pop ebx
pop eax
cmp eax, ebx
jne NOVA_false_cmp_1
push dword 1
jmp NOVA_end_cmp_1
NOVA_false_cmp_1:
push dword 0
NOVA_end_cmp_1:
push dword 1
pop ebx
pop eax
cmp eax, ebx
jne _end_if_NOVA1
pop eax
jmp _end_else_NOVA1
_end_if_NOVA1:
jmp NOVA_else_1
_else_NOVA1:
pop eax
_end_else_NOVA1:
mov esp, ebp
pop ebp
ret
_WinMain@16:
push ebp
mov ebp, esp
sub esp, 4
sub esp, 4
sub esp, 4
push dword 0
pop dword [ebp-8]
_while_MAIN1:
push dword [ebp-8]
push dword [ebp-4]
pop ebx
pop eax
cmp eax, ebx
jg MAIN_false_cmp_1
push dword 1
jmp MAIN_end_cmp_1
MAIN_false_cmp_1:
push dword 0
MAIN_end_cmp_1:
push dword 1
pop ebx
pop eax
cmp eax, ebx
jne _end_while_MAIN1
push dword [ebp-4]
call _nova
add esp, 4
pop dword [ebp-12]
push dword [ebp-12]
push dword 1
pop ebx
pop eax
cmp eax, ebx
jne _end_if_MAIN1
push dword 0000000
push dword intFormat
call _printf
add esp, 8
_end_if_MAIN1:
push dword [ebp-4]
push dword 1
pop ebx
pop eax
add eax, ebx
push eax
jmp _while_MAIN1
_end_while_MAIN1:
mov esp, ebp
pop ebp
ret


