IDENTIFICATORDIVISION. PROGRAMID. TestRule1IdDeclare.

	DATADIVISION.
	# Global variables
		globalIdBool PIC BOOLEAN.
		globalIdInt PIC INTEGER.
	EXIT.
	
	PROCEDUREDIVISION.	

		MAIN SECTION. INTEGER UselessParameterTestOnly

			BEGINDECLARATIONS
			# Local variables
				localIdBool PIC BOOLEAN.
				localIdInt PIC INTEGER.
			ENDDECLARATIONS
		
			MOVE 5 TO globalIdInt.
			MOVE TRUE TO globalIdBool.
			
			MOVE 7 TO localIdInt.
			MOVE FALSE TO localIdBool.
					
			DISPLAY (globalIdInt + localIdInt).
			DISPLAY (globalIdBool == localIdBool).
			
			ENDCOM.
									
		ENDPROC.

	EXIT.
