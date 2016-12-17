IDENTIFICATORDIVISION. PROGRAMID. PROGRAM1.

	DATADIVISION.
	# Global variables
		globalIdInt PIC INTEGER.
	EXIT.
	
	PROCEDUREDIVISION.	

		MAIN SECTION.

			BEGINDECLARATIONS
			# Local main variables
				localIdBool PIC BOOLEAN.
				localIdInt PIC INTEGER.
			ENDDECLARATIONS
			
			MOVE 5 TO globalIdInt.
			MOVE 3 TO localIdInt.
					
			IF ( globalIdInt > localIdInt )  THEN 
				MOVE TRUE TO localIdBool.
				ENDCOM.
			ELSE
				MOVE FALSE TO localIdBool.
				ENDCOM.
			ENDIF.
			
			DISPLAY localIdBool.
			
			ENDCOM.
		
		ENDPROC.
		
	EXIT.
