# TESTE

IDENTIFICATORDIVISION. PROGRAMID. SUBTRACAO.

	DATADIVISION.
		R PIC INTEGER.
	EXIT.

	PROCEDUREDIVISION.
	
		MAIN SECTION.
			
			BEGINDECLARATIONS
				A PIC INTEGER.
				B PIC INTEGER.
			ENDDECLARATIONS
			
			MOVE 7 TO A.
			MOVE 2 TO B.
			
			MOVE PERFORM SUBTRACAO USING A USING B TO R.
			
			IF R != 7 THEN
				DISPLAY 0.
				ENDCOM.
			ENDIF.
				
			ENDCOM.
			
		ENDPROC.
		
		SUBTRACAO SECTION. INTEGER N INTEGER M
			BEGINDECLARATIONS
			ENDDECLARATIONS
			
			RETURN (N - M).
			
			ENDCOM.
			
		ENDPROC.
	
	EXIT.
	
