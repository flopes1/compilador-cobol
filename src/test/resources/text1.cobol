# aojdasaodjaldjaldasldj
IDENTIFICATORDIVISION. PROGRAMID. TESTE

DATADIVISION.
	BOOLEAN B.
	INTEGER I.

PROCEDUREDIVISION.

	MAIN SECTION.
		
		R PIC INTEGER.
		X PIC INTEGER.
		MOVE 7 TO X.
		
		MOVE PERFORM SOMA USING X TO R.
		
		IF R = 3 THEN
			DISPLAY CERTO.
		ELSE
			DISPLAY ERRADO.
		
	ENDPROC.
	
	SOMA SECTION. INTEGER N
		RETURN N - 4.
	ENDPROC.

	
EXIT. 

# não consegui delimitar um ponto de fim do arquivo com o \000, sempre acontece estouro de memoria
# para corrigir isso utilizei @
@