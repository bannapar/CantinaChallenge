The jar file contains the class files and you can run the jar thru command line by the cmd
  'java -jar CantinaCodngTest.jar'

The java source files are added here.

The program retrieves the json thru web request . The selectors supported are 
  class - 'StackView','Content View', 'Input','Button'
  identifier - '#windowMode','#videoMode' (any control.identifer)

The output will be the json objects corresponding to the selector displayed in separate iines.
Fot the class selectors such 'StackView' and 'ContentView' all the descendants were also displayed . Hence I didnt support the StackView.Contaner explicitly because it is the same as StackView.
