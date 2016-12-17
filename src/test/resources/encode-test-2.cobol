IDENTIFICATORDIVISION. PROGRAMID. PROGRAM2.

	PROCEDUREDIVISION.	
				
		MAIN SECTION. 

			BEGINDECLARATIONS
				total PIC INTEGER.
				count PIC INTEGER.
				b PIC BOOLEAN.
			ENDDECLARATIONS
		
			MOVE TRUE TO b.
			MOVE 0 TO count.
			MOVE 5 TO total.
					
			UNTIL (count <= total) 

				IF b THEN 
					DISPLAY count.
					ENDCOM.
				ENDIF.
							
				MOVE (count + 1) TO count.
						
				ENDCOM.
				
			ENDWHILE.
		
			ENDCOM.
			
		ENDPROC.
			
	EXIT.
