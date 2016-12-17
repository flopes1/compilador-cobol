IDENTIFICATORDIVISION. PROGRAMID. PROGRAM4.
	
	PROCEDUREDIVISION.	
				
		MAIN SECTION. 

			BEGINDECLARATIONS
				total PIC INTEGER.
				count PIC INTEGER.
			ENDDECLARATIONS
		
			MOVE 0 TO count.
			MOVE 5 TO total.
					
			UNTIL (count <= total) 

				IF (count == 2) THEN
					MOVE (count + 1) TO count. 
					CONTINUE
					ENDCOM.
				ENDIF.
							
				DISPLAY count.
				MOVE (count + 1) TO count.
						
				ENDCOM.
				
			ENDWHILE.
		
			ENDCOM.
			
		ENDPROC.
			
	EXIT.
