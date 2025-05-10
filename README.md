# miniCrm
# PUBLIC 
- To add these (Assignment)
  - Local setup instructions
  -  Architecture diagram --> https://excalidraw.com/ and add db diagrm 
  -  Summary of AI tools and other tech used
  -  Known limitations or assumptions

## Features

- Register, Get all customer pagnited, get a customer, update and delete a customer

- added oauth2 using google login
- saving user when login for the first time  

## OAuth2
- public class OAuthServiceImpl implements OAuth2UserService<R,U>
- R -> google object , U-> my entity 
- we cannot directly save details from oauth2 to our db

# Segment
- customer count-> fetch from db and count according to rule -> then return that count

# Rule Eval Service
- using JPQL , custom query to fetch customer as per the segment rule / condition

# Security util
- using security to get details about currently logged in admin from spring security



# STRUCTURE
## 1. Admin 
- Admin creates rules (>1000 spend or account older thant 1 year)
- These rulese are stored in segment

## 2. Segment
- Filter , find customers with these rules
- show number of customers for these conditions / rules

## 3. Campaign
- Message template
- Using segment it knows where to send mesages

# 4. Communication log 
- Record/ history
-  TODO completee later

  ## Admin (creates rules) → Segment (finds matching customers) → Campaign (sends message) → CommunicationLog (tracks delivery)