IDENTIFICATORDIVISION. PROGRAMID. MeuPrograma15.
	
	PROCEDUREDIVISION.	
		
		minhaFuncao SECTION. INTEGER meuPrmt
		
			BEGINDECLARATIONS
				minhaVarLocal PIC INTEGER.
			ENDDECLARATIONS
	
			IF meuPrmt > 10 THEN
				MOVE 15 TO minhaVarLocal.
				ENDCOM.
			ELSE
				MOVE 30*30*30+30 TO minhaVarLocal.
				ENDCOM.
			ENDIF.
				
			RETURN minhaVarLocal.
					
			ENDCOM.
			
		ENDPROC.
		
	EXIT.
