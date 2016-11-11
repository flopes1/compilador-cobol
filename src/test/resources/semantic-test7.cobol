# RESTRICAO 7

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
							
				DISPLAY varInteger1 + varInteger2 - 1 - 2.
				DISPLAY 5 * 4 + (10/varInteger2) - 5.
				
				DISPLAY varBoolean1 == varBoolean2.
				DISPLAY varBoolean1 != varBoolean2.
				
				DISPLAY varInteger1 == varInteger2.
				DISPLAY varInteger1 != varInteger2.
				
				DISPLAY 5 + (varInteger1 * varInteger2) > 4.
				DISPLAY 4 >= (10 / varInteger2) / 5.
				
				
				ENDCOM.
				
			ENDPROC.
			
			
			
			
	EXIT.
