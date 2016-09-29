IDENTIFICATORDIVISION. PROGRAMID. meuPrograma17.
	DATADIVISION. 
		minhaVarGlobal PIC INTEGER.
	EXIT.
	
	PROCEDUREDIVISION.	
			minhaFuncao SECTION. INTEGER meuPrmt
				BEGINDECLARATIONS
		
				ENDDECLARATIONS
		
					MOVE meuPrmt*meuPrmt*meuPrmt + (50 / meuPrmt) TO minhaVarGlobal.
					
					IF minhaVarGlobal != 100 THEN
						DISPLAY minhaVarGlobal.
						CONTINUE
						ENDCOM.
					ENDIF.
							
					ENDCOM.
			
			ENDPROC.
	EXIT.
