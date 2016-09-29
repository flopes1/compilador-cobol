IDENTIFICATORDIVISION. PROGRAMID. testando.
	
	PROCEDUREDIVISION.	
			sec SECTION. INTEGER nomevar1 INTEGER nomevar2
				BEGINDECLARATIONS
					nomevar3 PIC INTEGER.
					nomevar5 PIC INTEGER.
					cont PIC INTEGER.
					result PIC INTEGER.
				ENDDECLARATIONS
		
					MOVE nomevar1 TO nomevar3.
					MOVE nomevar2 TO nomevar5.
					MOVE 0 TO cont.
					
					UNTIL cont <= nomevar2 
						MOVE PERFORM funcQuad USING varnome3 TO result.
						DISPLAY result.
						MOVE nomevar2 + 1 TO nomevar2.
						ENDCOM.
					ENDWHILE.
									
		
					ENDCOM.
			
			ENDPROC.
			
			funcQuad SECTION. INTEGER nomevar4
				BEGINDECLARATIONS
					
				ENDDECLARATIONS
		
					RETURN nomevar4*nomevar4.
		
					ENDCOM.
			
			ENDPROC.
	EXIT.
