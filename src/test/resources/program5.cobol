IDENTIFICATORDIVISION. PROGRAMID. SOMA.

	DATADIVISION.
		R PIC INTEGER.
	EXIT

	PROCEDUREDIVISION.
	
		MAIN SECTION.
			
			BEGINDECLARATIONS
				A PIC INTEGER.
				B PIC INTEGER.
			ENDDECLARATIONS
			
			MOVE 7 TO A.
			MOVE 2 TO B.
			
			MOVE PERFORM SOMA USING A USING B TO R.
			
			IF R = 9 THEN
				DISPLAY TRUE.
			ELSE
				DISPLAY FALSE.
				
			ENDCOM.
			
		ENDPROC.
		
		SOMA SECTION. INTEGER N INTEGER M
			BEGINDECLARATIONS
			ENDDECLARATIONS
			
			RETURN (N + M).
			
			ENDCOM.
			
		ENDPROC.
	
	EXIT.
	
