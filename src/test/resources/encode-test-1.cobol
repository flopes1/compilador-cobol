IDENTIFICATORDIVISION. PROGRAMID. PROGRAM1.

	DATADIVISION.
	# Global variables
		globalIdBool PIC BOOLEAN.
		globalIdInt PIC INTEGER.
	EXIT.
	
	PROCEDUREDIVISION.	

		MAIN SECTION. INTEGER intParameter BOOLEAN booleanParameter

			BEGINDECLARATIONS
			# Local main variables
				localIdBool PIC BOOLEAN.
				localIdInt PIC INTEGER.
			ENDDECLARATIONS
			
			DISPLAY localIdBool.
			
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
