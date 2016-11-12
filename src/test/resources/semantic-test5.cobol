IDENTIFICATORDIVISION. PROGRAMID. testeRetorno.
	
	PROCEDUREDIVISION.	
		
		INTEGER MAIN SECTION. INTEGER parametroInteiro

			BEGINDECLARATIONS
				varLocal1 PIC INTEGER.
			ENDDECLARATIONS
		
			MOVE parametroInteiro TO varLocal1.
			
			RETURN varLocal1+parametroInteiro.
		
			ENDCOM.
			
		ENDPROC.

	EXIT.