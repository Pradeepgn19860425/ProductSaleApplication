# ProductSaleApplication


ProductSaleApplication is a simple spring boot based application that processes different types of sale notification related messages

The application has two configurable parameters :
report.count = x .ie after processing of every x records,a sale report will be generated.
For the sake of quick testing, the value is current configured as 2
process.stop.count = y .ie generate adjustment report after processing y records and stop processing of further messages.
For the sake of quick testing,the value is currently configured as 4

The application uses Active MQ as the messaging platform to read and post messages

Technical stack :
Development language and version : Java 1.8
Build tool : Maven
Open Source Frameworks used : Spring,Active MQ



How to run the application :
1.Clone the project to your local repository using relevant GIT Client
2.Build the same using relevant maven commands
3.Run the spring boot annotated ProductSaleApplication as java application to trigger the spring boot application


