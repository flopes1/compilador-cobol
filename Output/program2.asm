extern _printf

SECTION .data

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
pop dword [ebp-4]
push dword 0
pop dword [ebp-8]
push dword 0
pop dword [ebp-12]
push dword 0
push dword [ebp-4]
pop ebx
pop eax
cmp eax, ebx
jge MAIN_false_cmp_1
push dword 1
jmp MAIN_end_cmp_1
MAIN_false_cmp_1:
push dword 0
MAIN_end_cmp_1:
push dword 1
pop ebx
pop eax
cmp eax, ebx
jne _end_if_MAIN1
_while_MAIN1:
push dword [ebp-8]
push dword [ebp-4]
pop ebx
pop eax
cmp eax, ebx
jg MAIN_false_cmp_2
push dword 1
jmp MAIN_end_cmp_2
MAIN_false_cmp_2:
push dword 0
MAIN_end_cmp_2:
push dword 1
pop ebx
pop eax
cmp eax, ebx
jne _end_while_MAIN1
push dword [ebp-4]
push dword intFormat
call _printf
add esp, 8
push dword [ebp-4]
push dword 1
pop ebx
pop eax
add eax, ebx
push eax
pop dword [ebp-4]
push dword 0
pop dword [ebp-12]
_while_MAIN2:
push dword [ebp-12]
push dword [ebp-4]
pop ebx
pop eax
cmp eax, ebx
jg MAIN_false_cmp_3
push dword 1
jmp MAIN_end_cmp_3
MAIN_false_cmp_3:
push dword 0
MAIN_end_cmp_3:
push dword 1
pop ebx
pop eax
cmp eax, ebx
jne _end_while_MAIN2
push dword 000000
push dword intFormat
call _printf
add esp, 8
push dword [ebp-12]
push dword 1
pop ebx
pop eax
add eax, ebx
push eax
pop dword [ebp-12]
jmp _while_MAIN2
_end_while_MAIN2:
jmp _while_MAIN2
_end_while_MAIN2:
_end_if_MAIN1:
mov esp, ebp
pop ebp
ret


