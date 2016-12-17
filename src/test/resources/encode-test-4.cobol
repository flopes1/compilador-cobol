IDENTIFICATORDIVISION. PROGRAMID. PROGRAM4.
	
	PROCEDUREDIVISION.	

		INTEGER funcQuad SECTION. INTEGER nomevar4

			BEGINDECLARATIONS
			ENDDECLARATIONS
		
			RETURN nomevar4*nomevar4.
		
			ENDCOM.
			
		ENDPROC.
		

		MAIN SECTION.

			BEGINDECLARATIONS
				nomevar3 PIC INTEGER.
				nomevar5 PIC INTEGER.
				cont PIC INTEGER.
				result PIC INTEGER.
			ENDDECLARATIONS
		
			MOVE 5 TO nomevar3.
			MOVE 4 TO nomevar5.
			MOVE 0 TO cont.
					
			UNTIL cont <= nomevar3 
				MOVE PERFORM funcQuad USING nomevar3 TO result.
				DISPLAY result.
				MOVE nomevar3 + 1 TO nomevar3.
				ENDCOM.
			ENDWHILE.
									
			ENDCOM.
			
		ENDPROC.
			


	EXIT.
