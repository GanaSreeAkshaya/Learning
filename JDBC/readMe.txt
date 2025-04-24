the advanced jdbc concepts include :

*Connection Pooling*
it is more effective way to connect db and application

->same as normal DBConnection but additionally uses-->Hitkari jar (used to create connection Pooling)
                                                   -->slf4j jar (support backend proccessing)
->more efficient as avoids outages during high load by making requesters wait untill service providers(connection obj) get free
->similar to thread pooling concepts
-> just replace the code in DBConnection with code in Hitkari.java and run with jar dependencies and it will work
