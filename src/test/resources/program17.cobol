IDENTIFICATORDIVISION. PROGRAMID. meuPrograma17.
	
	DATADIVISION. 
		minhaVarGlobal PIC INTEGER.
	EXIT.
	
	PROCEDUREDIVISION.	
	
		MAIN SECTION. INTEGER meuPrmt
	
			BEGINDECLARATIONS
			ENDDECLARATIONS
		
			MOVE meuPrmt*meuPrmt*meuPrmt + (50 / meuPrmt) TO minhaVarGlobal.
					
			IF minhaVarGlobal != 100 THEN
				DISPLAY minhaVarGlobal.
				ENDCOM.
			ENDIF.
							
			ENDCOM.
			
		ENDPROC.

	EXIT.
