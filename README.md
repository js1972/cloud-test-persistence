cloud-test-persistence
======================

Test persistence with SAP HANA Cloud Platform (shows JPA; Log on; Log off, etc) with the least amount of xml possible!

This app is managed with maven.

It demo's JPA with eclipselink 2.5.0; servlet 3.0 and ejb 3.0.

We have a basic JPA entity (Hello) which is used to count the number of times a user visits the app. A servlet filter is used to do the counting and update the db.
This app also uses the web.xml to show basic login capability and logout as well which is a little more tricky:
The lopout procedure si slightly different depending on whether the app is deployed on saphcp or the local server.
For an alternative method that uses java reflection to get hold of the loginContextFactory and allows the logout to work on non SAPHCP servers see the cloud-test repo.
