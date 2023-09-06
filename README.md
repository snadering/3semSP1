# 3semSP1
JPA Project
<img width="319" alt="Screenshot 2023-09-06 at 12 33 47" src="https://github.com/snadering/3semSP1/assets/113049401/1da48cbe-78c3-492d-9df4-2a4f13433161">


<h2>Postnummer (Zipcode): </h2>

Attributter:
Zip: Postnummeret.
City: Byen tilknyttet postnummeret.
Associering:
Én-til-mange med Adresse: Et postnummer kan være forbundet med flere adresser.
Adresse (Address):

Attributter:
Street: Gadenavnet på adressen.
Number: Husetnummeret på adressen.
Associering:
Én-til-mange med Begivenhed (Event): En adresse kan være forbundet med flere begivenheder.
Én-til-mange med Brugere (Users): En adresse kan være forbundet med flere brugere.
Én-til-én med Postnummer (Zipcode): En adresse har et forhold til ét postnummer.

<h2>Brugere (Users):</h2>

Attributter:
First name: Brugerens fornavn.
Surname: Brugerens efternavn.
Phone number: Brugerens kontakttelefonnummer.
Email: Brugerens e-mailadresse.
Associering:
Mange-til-mange med Hobby: Brugere kan have flere hobbyer, og hobbyer kan deles blandt flere brugere.
Mange-til-mange med Begivenhed (Event): Brugere kan deltage i flere begivenheder, og begivenheder kan have flere brugere.
Mange-til-én med Adresse: Brugere kan have én primær adresse.

<h2>Hobby:</h2>

Attributter:
Name: Navnet på hobbyen.
Category: Kategorien eller typen af hobbyen.
Associering:
Én-til-mange med Begivenhed (Event): En hobby kan være forbundet med flere begivenheder.
Mange-til-mange med Brugere: Hobbyer kan deles blandt flere brugere.

<h2>Begivenhed (Event):</h2>

Attributter:
Name: Navnet på begivenheden.
Price: Omkostninger eller pris forbundet med at deltage i begivenheden.
Associering:
Mange-til-mange med Brugere: Begivenheder kan have flere deltagere (brugere).
Mange-til-én med Adresse: Begivenheder er forbundet med én primær adresse.
Mange-til-mange med Hobby: Begivenheder kan være relateret til flere hobbyer.
