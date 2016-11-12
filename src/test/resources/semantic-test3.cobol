IDENTIFICATORDIVISION. PROGRAMID. TestRule3CallProcedureArgsCompatibility.

	DATADIVISION.
	# Global variables
		result PIC INTEGER.
	EXIT.
	
	PROCEDUREDIVISION.
	
		INTEGER CALC SECTION. INTEGER n1 INTEGER n2 BOOLEAN op
		
			BEGINDECLARATIONS
			ENDDECLARATIONS
			
			IF op THEN
				RETURN (n1 + n2).
				ENDCOM.
			ELSE
				RETURN (n1 - n2).
				ENDCOM.
			ENDIF.
			
			ENDCOM.
		
		ENDPROC.

		MAIN SECTION.

			BEGINDECLARATIONS
			# Local main variables
				number1 PIC INTEGER.
				number2 PIC INTEGER.
				option PIC BOOLEAN.
			ENDDECLARATIONS
			
			MOVE 7 TO number1.
			MOVE 10 TO number2.
			MOVE TRUE TO option.
			
			MOVE (PERFORM CALC USING number1 USING number2 USING option) TO result.

			DISPLAY result.
			
			ENDCOM.
		
		ENDPROC.
		
	EXIT.
