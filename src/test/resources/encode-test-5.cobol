IDENTIFICATORDIVISION. PROGRAMID. PROGRAM5.
	
	PROCEDUREDIVISION.	
		
		
		INTEGER sec2 SECTION. INTEGER nomevar4
	
			BEGINDECLARATIONS
			ENDDECLARATIONS
			
			RETURN nomevar4*10.
			
			ENDCOM.
				
		ENDPROC.
		
		INTEGER MAIN SECTION. INTEGER nomevar1 BOOLEAN nomevar2
			
			BEGINDECLARATIONS
				nomevar3 PIC INTEGER.
			ENDDECLARATIONS
			
			MOVE nomevar1 TO nomevar3.
					
			IF nomevar2 THEN 
				MOVE PERFORM sec2 USING nomevar3 TO nomevar3. 
				ENDCOM.
			ENDIF.
			
			DISPLAY nomevar3.
			
			RETURN nomevar3.
			
			ENDCOM.
				
		ENDPROC.
				
		
		
	EXIT.
