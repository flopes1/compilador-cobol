# RESTRICAO 8

IDENTIFICATORDIVISION. PROGRAMID. MYPROGRAM.
	DATADIVISION. 
		varInteger1 PIC INTEGER.
		varInteger2 PIC INTEGER.
		
		varBoolean1 PIC BOOLEAN.
		varBoolean2 PIC BOOLEAN.
	EXIT.
	
	PROCEDUREDIVISION.	
			MAIN SECTION.
				BEGINDECLARATIONS
		
		
				ENDDECLARATIONS
		
				MOVE (100 * 2 / 50 ) TO varInteger1.
				MOVE 1 + varInteger1 TO varInteger2.
				MOVE TRUE TO varBoolean1.
				MOVE FALSE TO varBoolean2.
				
				
				ENDCOM.
				
			ENDPROC.
			
			
			
			
	EXIT.
