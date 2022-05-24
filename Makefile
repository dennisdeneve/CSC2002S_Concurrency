JAVAC=/usr/bin/javac
.SUFFIXES: .java .class
SRCDIR=src
BINDIR=bin

$(BINDIR)/%.class:$(SRCDIR)/%.java
        $(JAVAC) -d $(BINDIR)/ -cp $(BINDIR) $<
        
CLASSES = Score.class Dictionary.class WordRecord.class WordPanel.class popup.class WordApp.class
CLASS_FILES=$(CLASSES:%.class=$(BINDIR)/%.class)

default: $(CLASS_FILES)

clean:
        rm $(BINDIR)/*.class
        
rungame: $(CLASS_FILES)
        java -cp $(BINDIR) WordApp

