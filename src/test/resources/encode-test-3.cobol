IDENTIFICATORDIVISION. PROGRAMID. PROGRAM3.
	
	PROCEDUREDIVISION.	
			
		MAIN SECTION. 
		
			BEGINDECLARATIONS
				nomevar2 PIC INTEGER.
				nomevar3 PIC INTEGER.
				nomevar4 PIC INTEGER.
			ENDDECLARATIONS
		
			MOVE 10 TO nomevar2.
			MOVE 0 TO nomevar3.
			MOVE 0 TO nomevar4.
					
			IF 0 < nomevar2  THEN 
		
				UNTIL nomevar3 <= nomevar2 
					
					DISPLAY nomevar3.
					MOVE nomevar3 + 1 TO nomevar3.
					MOVE 0 TO nomevar4.
		
					UNTIL nomevar4 <= nomevar2 
						DISPLAY 000000.
						MOVE nomevar4 + 1 TO nomevar4.
						ENDCOM.
					ENDWHILE.
					
					IF nomevar4 = 50 THEN 
						BREAK 
						ENDCOM.
					ENDIF.
					
					ENDCOM.
				ENDWHILE.
					
				ENDCOM.
			ENDIF.
						
			ENDCOM.
			
		ENDPROC.
			
	EXIT.
