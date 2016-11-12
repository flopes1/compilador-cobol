IDENTIFICATORDIVISION. PROGRAMID. testeRetorno.

#Esse teste verifica se os comandos Break e Continue estão dentro de um While.

	PROCEDUREDIVISION.	
		
		INTEGER MAIN SECTION. INTEGER prd1

			BEGINDECLARATIONS
				count PIC INTEGER.
			ENDDECLARATIONS
		
			MOVE 10 TO count.
				
			UNTIL count > 0
				DISPLAY count.
				IF count == prd1 THEN
					BREAK
					ENDCOM.
				ENDIF.
				
				ENDCOM.
			ENDWHILE.
			
			RETURN count.
			
			ENDCOM.
			
		ENDPROC.

	EXIT.