IDENTIFICATORDIVISION. PROGRAMID. testando.
	
	PROCEDUREDIVISION.	
			
		sec SECTION. INTEGER nomevar1
		
			BEGINDECLARATIONS
				nomevar2 PIC INTEGER.
				nomevar3 PIC INTEGER.
				nomevar4 PIC INTEGER.
			ENDDECLARATIONS
		
			MOVE nomevar1 TO nomevar2.
			MOVE 0 TO nomevar3.
			MOVE 0 TO nomevar4.
					
			IF 0 < nomevar2  THEN 
		
				UNTIL nomevar3 <= nomevar2 
					
					DISPLAY nomevar2.
					MOVE nomevar2 + 1 TO nomevar2.
					MOVE 0 TO nomevar4.
		
					UNTIL nomevar4 <= nomevar2 
						DISPLAY 000000.
						MOVE nomevar4 + 1 TO nomevar4.
						ENDCOM.
					ENDWHILE.
		
					ENDCOM.
				ENDWHILE.
					
				ENDCOM.
			ENDIF.
						
			ENDCOM.
			
		ENDPROC.
			
	EXIT.
