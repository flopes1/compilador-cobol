IDENTIFICATORDIVISION. PROGRAMID. PROGRAM1.

	DATADIVISION.
	# Global variables
		globalIdBool PIC BOOLEAN.
		globalIdInt PIC INTEGER.
	EXIT.
	
	PROCEDUREDIVISION.	

		MAIN SECTION. INTEGER intParameter BOOLEAN booleanParameter INTEGER intParameter2

			BEGINDECLARATIONS
			# Local main variables
				localIdBool PIC BOOLEAN.
				localIdInt PIC INTEGER.
				localIdInt2 PIC INTEGER.
			ENDDECLARATIONS
			
			MOVE intParameter + 10 TO localIdInt2.
			DISPLAY (5>9) .
			
			ENDCOM.
		
		ENDPROC.
		
		FUNCTION SECTION. INTEGER intParameter
		
			BEGINDECLARATIONS
			# Local function variables
				localIdBool PIC BOOLEAN.
				localIdInt PIC INTEGER.
			ENDDECLARATIONS
			
			DISPLAY localIdInt.
			
			ENDCOM.
		
		ENDPROC.

	EXIT.
