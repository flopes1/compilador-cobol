IDENTIFICATORDIVISION. PROGRAMID. OPERACOES.

	PROCEDUREDIVISION.
	
		MAIN SECTION.
			
			BEGINDECLARATIONS
				A PIC INTEGER.
				B PIC INTEGER.
			ENDDECLARATIONS
			
			MOVE 8 TO A.
			MOVE 2 TO B.
			
			IF A < B THEN
				DISPLAY (A * B).
				ENDCOM.
			ELSE
				DISPLAY (A / B).
				ENDCOM.
				
			ENDCOM.
			
		ENDPROC.
	
	EXIT.
	
