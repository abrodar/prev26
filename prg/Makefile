JAVA            = java
ANTLRDIR        = ../lib/antlr-4.13.2-complete.jar
XSLTPROC	= xsltproc
RM              = 'rm' -fr

PHASE		= all
TARGETPHASE	?= $(PHASE)
LOGGEDPHASE	?= $(PHASE)

.PHONY	: %
%	: %.p26
	$(RM) -fr $@-*.xml $@-*.html
	if $(JAVA) -cp ../bin:../src:$(ANTLRDIR) prev26lang.Compiler --xsl=../lib/xsl/ --logged-phase=$(LOGGEDPHASE) --target-phase=$(TARGETPHASE) $(OPTIONS) $< ; then for XML in `'find' . -iname $@"-*.xml"` ; do $(XSLTPROC) $$XML -o `basename $$XML xml`html; echo "<script src=p26.js data-deferarrows=true></script>" >> `basename $$XML xml`html; done ; fi
# data-deferarrows=true povzroči, da se puščice pokažejo šele ob stisku tipke Enter
# to je zato, da ne zmotim slivnikovega ocenjevalnega procesa s potencialno motečimi puščicami
# odstrani data-deferarrows=true, da se puščice pokažejo, čim odpreš stran

.PHONY  : clean
clean   :
	$(RM) *~
	$(RM) *xml *html

.PHONY: all_tests
all_tests:
	for f in *.p26 ; do \
	    make $${f%.p26} TARGETPHASE=imrlin																																																																													 ; \
	done