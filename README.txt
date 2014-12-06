UTEID: kdh2289; jhm2464;
FIRSTNAME: Kaelin; Jeremiah;
LASTNAME: Hooper; Martinez;
CSACCOUNT: kaelin; jeremiah;
EMAIL: kaelin@cs.utexas.edu; Jeremiah.H.Martinez@utexas.edu;

[Program 6]
[Description]
There is only one file, the file that holds all the power in the universe. In order to run this program,
use "PasswordCrack dicFile pwFile" where dicFile is the file of possible words used for a password
and pwFile is the file containing the encrypted passwords along with their associated info.
The most this program will check for is 2 mangles. Before the dictionary is read, the program checks
to see if the username and user's name are used as a password.
All the mangling methods except append/prepend are decided by a switch/case method whichMangle() 
since this was the easiest way to check for all combos of 2 mangles (two for loops that feed their iteration into whichMangle()).
For the dictionary words, all 1 and 2 mangles are checked first, except for mangles 2 mangles that involve appending/prepending. 2 mangles involving appending/prepending is done last since these take the long to decrypt.

[Finish]
We finished all requirements. For our given mangling methods, the only possible set of passwords we don't check is 2 mangle append/prepend of the username and user's name.

[Test Cases]
[Input of test 1]

michael:atbWfKL4etk4U:500:500:Michael Ferris:/home/michael:/bin/bash		
abigail:&i4KZ5wmac566:501:501:Abigail Smith:/home/abigail:/bin/tcsh
samantha:(bUx9LiAcW8As:502:502:Samantha Connelly:/home/samantha:/bin/bash
tyler:<qt0.GlIrXuKs:503:503:Tyler Jones:/home/tyler:/bin/tcsh
alexander:feohQuHOnMKGE:504:504:Alexander Brown:/home/alexander:
james:{ztmy9azKzZgU:505:505:James Dover:/home/james:/bin/bash
benjamin:%xPBzF/TclHvg:506:506:Benjamin Ewing:/home/benjamin:/bin/bash
morgan:khnVjlJN3Lyh2:507:507:Morgan Simmons:/home/morgan:/bin/bash
jennifer:e4DBHapAtnjGk:508:508:Jennifer Elmer:/home/jennifer:/bin/bash
nicole:7we09tBSVT76o:509:509:Nicole Rizzo:/home/nicole:/bin/tcsh
evan:3dIJJXzELzcRE:510:510:Evan Whitney:/home/evan:/bin/bash
jack:jsQGVbJ.IiEvE:511:511:Jack Gibson:/home/jack:/bin/bash
victor:w@EbBlXGLTue6:512:512:Victor Esperanza:/home/victor:
caleb:8joIBJaXJvTd2:513:513:Caleb Patterson:/home/caleb:/bin/bash
nathan:nxsr/UAKmKnvo:514:514:Nathan Moore:/home/nathan:/bin/ksh
connor:gwjT8yTnSCVQo:515:515:Connor Larson:/home/connor:/bin/bash
rachel:KelgNcBOZdHmA:516:516:Rachel Saxon:/home/rachel:/bin/bash
dustin:5WW698tSZJE9I:517:517:Dustin Hart:/home/dustin:/bin/csh
maria:!cI6tOT/9D2r6:518:518:Maia Salizar:/home/maria:/bin/zsh
paige:T8jwuve9rQBo.:519:519:Paige Reiser:/home/paige:/bin/bash

[Output of test 1]

Password cracked! Account name: michael	Encrypted password: atbWfKL4etk4U	Plaintext password: michael
Password cracked! Account name: abigail	Encrypted password: &i4KZ5wmac566	Plaintext password: liagiba
Password cracked! Account name: maria	Encrypted password: !cI6tOT/9D2r6	Plaintext password: Salizar
Password cracked! Account name: benjamin	Encrypted password: %xPBzF/TclHvg	Plaintext password: abort6
Password cracked! Account name: samantha	Encrypted password: (bUx9LiAcW8As	Plaintext password: amazing
Password cracked! Account name: tyler	Encrypted password: <qt0.GlIrXuKs	Plaintext password: eeffoc
Password cracked! Account name: morgan	Encrypted password: khnVjlJN3Lyh2	Plaintext password: rdoctor
Password cracked! Account name: jennifer	Encrypted password: e4DBHapAtnjGk	Plaintext password: doorrood
Password cracked! Account name: connor	Encrypted password: gwjT8yTnSCVQo	Plaintext password: enoggone
Password cracked! Account name: evan	Encrypted password: 3dIJJXzELzcRE	Plaintext password: Impact
Password cracked! Account name: nicole	Encrypted password: 7we09tBSVT76o	Plaintext password: keyskeys
Password cracked! Account name: caleb	Encrypted password: 8joIBJaXJvTd2	Plaintext password: teserP
Password cracked! Account name: jack	Encrypted password: jsQGVbJ.IiEvE	Plaintext password: sATCHEL
Password cracked! Account name: alexander	Encrypted password: feohQuHOnMKGE	Plaintext password: squadro
Password cracked! Account name: victor	Encrypted password: w@EbBlXGLTue6	Plaintext password: THIRTY
Password cracked! Account name: james	Encrypted password: {ztmy9azKzZgU	Plaintext password: icious
Password cracked! Account name: rachel	Encrypted password: KelgNcBOZdHmA	Plaintext password: obliqu3
Password cracked! Account name: nathan	Encrypted password: nxsr/UAKmKnvo	Plaintext password: sHREWDq


[Input of test 2]

michael:atQhiiJLsT6cs:500:500:Michael Ferris:/home/michael:/bin/bash		
abigail:&ileDTgJtzCRo:501:501:Abigail Smith:/home/abigail:/bin/tcsh
samantha:(bt0xiAwCf7nA:502:502:Samantha Connelly:/home/samantha:/bin/bash
tyler:<qf.L9z1/tZkA:503:503:Tyler Jones:/home/tyler:/bin/tcsh
alexander:fe8PnYhq6WoOQ:504:504:Alexander Brown:/home/alexander:
james:{zQOjvJcHMs7w:505:505:James Dover:/home/james:/bin/bash
benjamin:%xqFrM5RVA6t6:506:506:Benjamin Ewing:/home/benjamin:/bin/bash
morgan:kh/1uC5W6nDKc:507:507:Morgan Simmons:/home/morgan:/bin/bash
jennifer:e4EyEMhNzYLJU:508:508:Jennifer Elmer:/home/jennifer:/bin/bash
nicole:7wKTWsgNJj6ac:509:509:Nicole Rizzo:/home/nicole:/bin/tcsh
evan:3d1OgBYfvEtfg:510:510:Evan Whitney:/home/evan:/bin/bash
jack:js5ctN1leUABo:511:511:Jack Gibson:/home/jack:/bin/bash
victor:w@FxBZv.d0y/U:512:512:Victor Esperanza:/home/victor:
caleb:8jGWbU0xbKz06:513:513:Caleb Patterson:/home/caleb:/bin/bash
nathan:nxr9OOqvZjbGs:514:514:Nathan Moore:/home/nathan:/bin/ksh
connor:gw9oXmw1L08RM:515:515:Connor Larson:/home/connor:/bin/bash
rachel:KenK1CTDGr/7k:516:516:Rachel Saxon:/home/rachel:/bin/bash
dustin:5Wb2Uqxhoyqfg:517:517:Dustin Hart:/home/dustin:/bin/csh
maria:!cSaQELm/EBV.:518:518:Maia Salizar:/home/maria:/bin/zsh
paige:T8U5jQaDVv/o2:519:519:Paige Reiser:/home/paige:/bin/bash

[Output of test 2]

Password cracked! Account name: samantha	Encrypted password: (bt0xiAwCf7nA	Plaintext password: cOnNeLlY
Password cracked! Account name: jennifer	Encrypted password: e4EyEMhNzYLJU	Plaintext password: ElmerJ
Password cracked! Account name: connor	Encrypted password: gw9oXmw1L08RM	Plaintext password: nosral
Password cracked! Account name: abigail	Encrypted password: &ileDTgJtzCRo	Plaintext password: Saxon
Password cracked! Account name: evan	Encrypted password: 3d1OgBYfvEtfg	Plaintext password: ^bribed
Password cracked! Account name: morgan	Encrypted password: kh/1uC5W6nDKc	Plaintext password: dIAMETER
Password cracked! Account name: james	Encrypted password: {zQOjvJcHMs7w	Plaintext password: enchant$
Password cracked! Account name: tyler	Encrypted password: <qf.L9z1/tZkA	Plaintext password: eltneg
Password cracked! Account name: nicole	Encrypted password: 7wKTWsgNJj6ac	Plaintext password: INDIGNITY
Password cracked! Account name: alexander	Encrypted password: fe8PnYhq6WoOQ	Plaintext password: Lacque
Password cracked! Account name: michael	Encrypted password: atQhiiJLsT6cs	Plaintext password: tremors
Password cracked! Account name: jack	Encrypted password: js5ctN1leUABo	Plaintext password: ellows
Password cracked! Account name: caleb	Encrypted password: 8jGWbU0xbKz06	Plaintext password: zoossooz
Password cracked! Account name: benjamin	Encrypted password: %xqFrM5RVA6t6	Plaintext password: soozzoos
Password cracked! Account name: dustin	Encrypted password: 5Wb2Uqxhoyqfg	Plaintext password: Swine3
Password cracked! Account name: nathan	Encrypted password: nxr9OOqvZjbGs	Plaintext password: uPLIFTr

