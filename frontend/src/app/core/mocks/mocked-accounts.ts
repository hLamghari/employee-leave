export function getMockedAccountById(id: any) {
  let account =  MOCKED_GROUPED_ACCOUNTS.currentAccounts.find(account => account.id === id);
  if(account){
    return account;
  } 
  return MOCKED_GROUPED_ACCOUNTS.savingsAccounts.find(account => account.id === id);
}

export const MOCKED_GROUPED_ACCOUNTS = {
    "currentAccounts": [
      {
        "id": 1,
        "nom": "Account 1",
        "iban": "FR24383017829792287069665",
        "type": "CURRENT_ACCOUNT",
        "amount": 9480.82,
        "tauxInteret": 0.0
      },
      {
        "id": 5,
        "nom": "Account 5",
        "iban": "FR47878701625087090138991",
        "type": "CURRENT_ACCOUNT",
        "amount": 7683.53,
        "tauxInteret": 0.0
      },
      {
        "id": 8,
        "nom": "Account 8",
        "iban": "FR10730040060530461094919",
        "type": "CURRENT_ACCOUNT",
        "amount": 5319.53,
        "tauxInteret": 0.0
      },
      {
        "id": 9,
        "nom": "Account 9",
        "iban": "FR47433271687789479061671",
        "type": "CURRENT_ACCOUNT",
        "amount": 6036.94,
        "tauxInteret": 0.0
      },
      {
        "id": 12,
        "nom": "Account 12",
        "iban": "FR59070899358014138329816",
        "type": "CURRENT_ACCOUNT",
        "amount": 6431.77,
        "tauxInteret": 0.0
      },
      {
        "id": 15,
        "nom": "Account 15",
        "iban": "FR35317611585972407344676",
        "type": "CURRENT_ACCOUNT",
        "amount": 2062.64,
        "tauxInteret": 0.0
      },
      {
        "id": 19,
        "nom": "Account 19",
        "iban": "FR72361082980697594705209",
        "type": "CURRENT_ACCOUNT",
        "amount": 4900.05,
        "tauxInteret": 0.0
      },
      {
        "id": 20,
        "nom": "Account 20",
        "iban": "FR36159304239434926765054",
        "type": "CURRENT_ACCOUNT",
        "amount": 3330.5,
        "tauxInteret": 0.0
      }
    ],
    "savingsAccounts": [
      {
        "id": 2,
        "nom": "Account 2",
        "iban": "FR63417144000630303903711",
        "type": "SAVINGS_ACCOUNT",
        "amount": 3419.09,
        "tauxInteret": 0.58
      },
      {
        "id": 3,
        "nom": "Account 3",
        "iban": "FR33801420030556261970524",
        "type": "SAVINGS_ACCOUNT",
        "amount": 9408.27,
        "tauxInteret": 3.11
      },
      {
        "id": 4,
        "nom": "Account 4",
        "iban": "FR99466580130987065477658",
        "type": "SAVINGS_ACCOUNT",
        "amount": 8969.91,
        "tauxInteret": 4.4
      },
      {
        "id": 6,
        "nom": "Account 6",
        "iban": "FR08325084400314285188868",
        "type": "SAVINGS_ACCOUNT",
        "amount": 4950.67,
        "tauxInteret": 2.99
      },
      {
        "id": 7,
        "nom": "Account 7",
        "iban": "FR00008667074753927109754",
        "type": "SAVINGS_ACCOUNT",
        "amount": 9714.08,
        "tauxInteret": 3.56
      },
      {
        "id": 10,
        "nom": "Account 10",
        "iban": "FR61327577535936339399521",
        "type": "SAVINGS_ACCOUNT",
        "amount": 2007.81,
        "tauxInteret": 1.91
      },
      {
        "id": 11,
        "nom": "Account 11",
        "iban": "FR22349629594105823510294",
        "type": "SAVINGS_ACCOUNT",
        "amount": 1134.3,
        "tauxInteret": 4.79
      },
      {
        "id": 13,
        "nom": "Account 13",
        "iban": "FR76888916326176811403577",
        "type": "SAVINGS_ACCOUNT",
        "amount": 9196.99,
        "tauxInteret": 1.54
      },
      {
        "id": 14,
        "nom": "Account 14",
        "iban": "FR73926047795253633175574",
        "type": "SAVINGS_ACCOUNT",
        "amount": 3237.59,
        "tauxInteret": 1.45
      },
      {
        "id": 16,
        "nom": "Account 16",
        "iban": "FR82153742983205629907733",
        "type": "SAVINGS_ACCOUNT",
        "amount": 5624.86,
        "tauxInteret": 3.58
      },
      {
        "id": 17,
        "nom": "Account 17",
        "iban": "FR99979688091443119642444",
        "type": "SAVINGS_ACCOUNT",
        "amount": 5246.84,
        "tauxInteret": 2.73
      },
      {
        "id": 18,
        "nom": "Account 18",
        "iban": "FR47403556955483573703930",
        "type": "SAVINGS_ACCOUNT",
        "amount": 7818.7,
        "tauxInteret": 1.77
      }
    ]
  }
  
  export const MOCKED_ALL_ACCOUNTS = [
      {
        "id": 1,
        "nom": "Account 1",
        "iban": "FR24383017829792287069665",
        "type": "CURRENT_ACCOUNT",
        "amount": 9480.82,
        "tauxInteret": 0.0
      },
      {
        "id": 5,
        "nom": "Account 5",
        "iban": "FR47878701625087090138991",
        "type": "CURRENT_ACCOUNT",
        "amount": 7683.53,
        "tauxInteret": 0.0
      },
      {
        "id": 8,
        "nom": "Account 8",
        "iban": "FR10730040060530461094919",
        "type": "CURRENT_ACCOUNT",
        "amount": 5319.53,
        "tauxInteret": 0.0
      },
      {
        "id": 9,
        "nom": "Account 9",
        "iban": "FR47433271687789479061671",
        "type": "CURRENT_ACCOUNT",
        "amount": 6036.94,
        "tauxInteret": 0.0
      },
      {
        "id": 12,
        "nom": "Account 12",
        "iban": "FR59070899358014138329816",
        "type": "CURRENT_ACCOUNT",
        "amount": 6431.77,
        "tauxInteret": 0.0
      },
      {
        "id": 15,
        "nom": "Account 15",
        "iban": "FR35317611585972407344676",
        "type": "CURRENT_ACCOUNT",
        "amount": 2062.64,
        "tauxInteret": 0.0
      },
      {
        "id": 19,
        "nom": "Account 19",
        "iban": "FR72361082980697594705209",
        "type": "CURRENT_ACCOUNT",
        "amount": 4900.05,
        "tauxInteret": 0.0
      }
    ]