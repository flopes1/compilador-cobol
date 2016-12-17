IDENTIFICATORDIVISION. PROGRAMID. PROGRAM2.
	
	PROCEDUREDIVISION.	
		
		
			BOOLEAN NOVA SECTION. INTEGER nomevar10
		
			BEGINDECLARATIONS
				nomevar11 PIC INTEGER.
			ENDDECLARATIONS
					
			MOVE nomevar10 + 1 TO nomevar11.
					
			IF ( nomevar11 = 20 )  THEN 
				RETURN TRUE.
				ENDCOM.
			ELSE
				RETURN FALSE.
				ENDCOM.
			ENDIF.
		
			ENDCOM.
			
		ENDPROC.
		
		
		MAIN SECTION. 

			BEGINDECLARATIONS
				nomevar2 PIC INTEGER.
				cont PIC INTEGER.
				b PIC BOOLEAN.
			ENDDECLARATIONS
		
			MOVE 0 TO cont.
					
			UNTIL  cont <= nomevar2 

				MOVE PERFORM NOVA USING nomevar2 TO b.

				IF ( b )  THEN 
					DISPLAY 0000000.
					ENDCOM.
				ENDIF.
							
				MOVE nomevar2 + 1 TO nomevar2.
						
				ENDCOM.
			ENDWHILE.
		
			ENDCOM.
			
		ENDPROC.
			
	

	EXIT.
