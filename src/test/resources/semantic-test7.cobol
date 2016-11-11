IDENTIFICATORDIVISION. PROGRAMID. idQualquer.
	DATADIVISION. 
		varInteger1 PIC INTEGER.
		varInteger2 PIC INTEGER.
		
		varBoolean1 PIC BOOLEAN.
		varBoolean2 PIC BOOLEAN.
	EXIT.
	
	PROCEDUREDIVISION.	
			#COMEÇO DE UM PROCEDURE(PODE NAO TER)
			procedure1 SECTION. INTEGER parametroInteiro
				BEGINDECLARATIONS
		
		
				ENDDECLARATIONS
		
				MOVE 1 TO varInteger1.
				MOVE 1+1 TO varInteger2.
				MOVE TRUE TO varBoolean1.
				MOVE FALSE TO varBoolean2.
				
							
				DISPLAY varInteger1 + varInteger2 - 1 - 2.
				DISPLAY 5 * 4 + (10/varInteger2) - 5.
				
				DISPLAY varBoolean1 == varBoolean2.
				DISPLAY varBoolean1 != varBoolean2.
				
				DISPLAY varInteger1 == varInteger2.
				DISPLAY varInteger1 != varInteger2.
				
				DISPLAY 5 + varInteger1 > 4.
				DISPLAY 4 >= 10 / varInteger2.
				
				
				ENDCOM.
				
			ENDPROC.
			
			
			
			
	EXIT.
