from faker import Faker
import random
import datetime

fake = Faker(['cs_CZ'])

ALL_DATA = 32000

NUMBER_OF_CUSTOMERS = 30000
NUMBER_OF_EMPLOYEES = 4000
NUMBER_OF_TRAINERS = 4000

ID = 0


def generate_person_data():
    national_id = fake.pystr_format(string_format='######/####')
    phone = fake.phone_number()
    date_of_birth = fake.date_of_birth(minimum_age=13).strftime('%Y-%m-%d')
    first_name = fake.first_name()
    last_name = fake.last_name()
    city = fake.city()
    street = fake.street_name()
    postal_code = fake.postcode()
    return (national_id, phone, date_of_birth, first_name, last_name, city, street, postal_code)

def generate_employee_data(person_data, i):
    national_id = person_data[i][0]
    contract_number = i + 1000      # offset from 1000
    work_position = random.choice(
        ['Receptionist', 'Fyzioterapeut', 'Immunologist', 'Financial manager', 
         'Food technologist', 'Nutritional therapist', 'Cleaner', 'Massage therapist'])
    salary = round(random.uniform(1000, 10000), 2)
    return (national_id, contract_number, work_position, salary)

def generate_customer_data(person_data, i):
    national_id = person_data[i][0]
    return (national_id)

def generate_trainer_data(person_data, i):
    national_id = person_data[i][0]
    return (national_id)

def generate_supervise_data(employees, i):
    hasSupervisor = random.choice([True, False])
    supervised = employees[i]
    supervisor = random.choice(employees)
    while supervisor == supervised:
        supervisor = random.choice(employees)
    if hasSupervisor:
        return (supervised[0], supervisor[0])
    else:
        return None

def generate_invoice_data(employees, i):
    invoice_number = i + 1000
    employee = random.choice(employees)[0]
    cost = round(random.uniform(10, 100), 2)
    return (invoice_number, employee, cost)

def generate_membership_data(customers, invoice, i):
    if i < (NUMBER_OF_CUSTOMERS):
        customer = customers[i]
    else:
        customer = random.choice(customers)
    membership_type = random.choice(['Basic', 'Premium', 'Student', 'One Time', '20 Times'])
    invoice = invoice[0]
    expire_date = fake.future_date(end_date='+1y').strftime('%Y-%m-%d')
    money_cost = round(random.uniform(10, 100), 2)
    return (membership_type, customer, invoice, expire_date, money_cost)

def generate_trainers_license_data(trainers, i):
    if i < NUMBER_OF_TRAINERS:
        trainer = trainers[i]
    else:
        trainer = random.choice(trainers)
    license = random.choice(
        ['Yoga instructor', 'Zumba instructor','PowerLifting instructor', 
         'Strongman instructor', 'Bicycle instructor'])
    return (trainer, license)

def generate_workout_class_data(trainers):
    id = random.randint(1, 100000)
    name = random.choice(
        ['Yoga', 'Power Yoga', 'Pilates', 'PowerLifting',
         'Stretching', 'Abs training', 'CrossFit', 'Zumba'])
    day = fake.date_between(start_date='today', end_date='+1y').strftime('%Y-%m-%d')
    time = fake.time_object().strftime('%H:00:00')
    teacher = random.choice(trainers)
    capacity = random.randint(5, 20)
    return (id, name, day, time, teacher, capacity)

def generate_take_class_data(customers, workout_classes):
    customer = random.choice(customers)
    random_class = random.choice(workout_classes)
    id = random_class[0]
    # name = random_class[1]
    # day = random_class[2]
    # time = random_class[3]
    # teacher = random_class[4]
    price = round(random.uniform(5, 20), 2)
    return (id, customer, price)


def is_person_in(old_data, new_data):
    for j in old_data:
        if new_data[0] == j[0] or new_data[1] == j[1]:
            return True
    return False

def is_license_in(old_data, new_data):
    for j in old_data:
        if new_data[0] == j[0] and new_data[1] == j[1]:
            return True
    return False

def is_workout_in(old_data, new_data):
    for j in old_data:
        if (new_data[0] == j[0]) or (new_data[1] == j[1] and new_data[2] == j[2] and new_data[3] == j[3] and new_data[4] == j[4]):
            return True
    return False

def is_class_in(old_data, new_data):
    for j in old_data:
        # if new_data[0] == j[0] and new_data[1] == j[1] and new_data[2] == j[2] and new_data[3] == j[3] and new_data[4] == j[4]:
        if new_data[0] == j[0] and new_data[1] == j[1]:
            return True
    return False

def is_mem_in(old_data, new_data):
    for j in old_data:
        if new_data[0] == j[0] and new_data[1] == j[1] and new_data[2] == j[2]:
            return True
    return False
def is_inv_in(old_data, new_data):
    for j in old_data:
        if new_data[0] == j[0]:
            return True
    return False

# ---- Generate data for each table ----
def get_person_data():
    person_data = []
    for i in range(ALL_DATA):
        new_data = generate_person_data()
        if is_person_in(person_data, new_data):
            continue
        person_data.append(new_data)
    return person_data

def get_workout_classes_data(trainers):
    workout_classes_data = []
    for i in range(NUMBER_OF_TRAINERS//2):
        new_data = generate_workout_class_data(trainers)
        if is_workout_in(workout_classes_data, new_data):
            continue
        workout_classes_data.append(new_data)
    return workout_classes_data


def get_take_class_data(workout_classes_data, customers):
    take_classes_data = []
    for i in range(NUMBER_OF_TRAINERS*4):
        new_data = generate_take_class_data(customers, workout_classes_data)
        if is_class_in(take_classes_data, new_data):
            continue
        take_classes_data.append(new_data)
    return take_classes_data

def get_license_data(trainers):
    trainers_license_data = []
    for i in range(NUMBER_OF_TRAINERS+(random.randint(0, NUMBER_OF_TRAINERS)//2)):
        new_data = generate_trainers_license_data(trainers, i)
        if is_license_in(trainers_license_data, new_data):
            continue
        trainers_license_data.append(new_data)
    return trainers_license_data

def get_membership_and_invoice_data(customer_data, employee_data):
    membership_data = []
    invoice_data = []
    for i in range(NUMBER_OF_CUSTOMERS+(NUMBER_OF_CUSTOMERS//16)):
        new_inv = generate_invoice_data(employee_data, i)
        new_mem = generate_membership_data(customer_data, new_inv, i)
        if is_inv_in(invoice_data, new_inv) or is_mem_in(membership_data, new_mem):
            continue
        invoice_data.append(new_inv)
        membership_data.append(new_mem)
    return membership_data, invoice_data

def get_supervise_data(employee_data):
    supervise_data = []
    for i in range(NUMBER_OF_EMPLOYEES):
        new_data = generate_supervise_data(employee_data, i)
        if new_data != None:
            supervise_data.append(new_data)
    return supervise_data

# persons = generate ~32k unique persons
person_data = get_person_data()
# employee = 0-1000 person is employee
employee_data = [generate_employee_data(person_data, i) for i in range(1900, NUMBER_OF_EMPLOYEES + 1900)]
# trainer = 1000-2000 person is trainer
trainer_data = [generate_trainer_data(person_data, i) for i in range(0, NUMBER_OF_TRAINERS)]
# customer = 2000-len(person_data) is customer
customer_data = [generate_customer_data(person_data, i) for i in range(len(person_data)-NUMBER_OF_CUSTOMERS, len(person_data))]
trainers_license_data = get_license_data(trainer_data)
# supervise filter (if not has supervisor do not put in table)
supervise_data = get_supervise_data(employee_data)

membership_data, invoice_data = get_membership_and_invoice_data(customer_data, employee_data)
workout_classes_data = get_workout_classes_data(trainer_data)
take_classes_data = get_take_class_data(workout_classes_data, customer_data)



# --------- Write data to file ----------
with open("data_updated.sql", 'w') as file:
    file.write("INSERT INTO Person (nationalId, phone, birth, firstName, lastName, city, street, postalCode) VALUES \n")
    cnt = 1
    for i in person_data:
        file.write(str(i))
        if cnt < len(person_data):
            file.write(",\n")
        else:
            file.write(";\n")
        cnt +=1
    file.write("\n\n")

    file.write("INSERT INTO Employee (nationalId, contractNumber, workPosition, salary) VALUES  \n")
    cnt = 1
    for i in employee_data:
        file.write(str(i))
        if cnt < len(employee_data):
            file.write(",\n")
        else:
            file.write(";\n")
        cnt +=1
    file.write("\n\n")

    file.write("INSERT INTO Customer (nationalId) VALUES  \n")
    cnt = 1
    for i in customer_data:
        file.write('(\''+str(i)+'\')')
        if cnt < len(customer_data):
            file.write(",\n")
        else:
            file.write(";\n")
        cnt +=1
    file.write("\n\n")

    file.write("INSERT INTO Trainer (nationalId) VALUES   \n")
    cnt = 1
    for i in trainer_data:
        file.write('(\''+str(i)+'\')')
        if cnt < len(trainer_data):
            file.write(",\n")
        else:
            file.write(";\n")
        cnt +=1
    file.write("\n\n")

    file.write("INSERT INTO Supervise (supervised, supervisor) VALUES  \n")
    cnt = 1
    for i in supervise_data:
        file.write(str(i))
        if cnt < len(supervise_data):
            file.write(",\n")
        else:
            file.write(";\n")
        cnt +=1
    file.write("\n\n")

    file.write("INSERT INTO Invoice (invoiceNumber, employee, cost) VALUES   \n")
    cnt = 1
    for i in invoice_data:
        file.write(str(i))
        if cnt < len(invoice_data):
            file.write(",\n")
        else:
            file.write(";\n")
        cnt +=1
    file.write("\n\n")

    file.write("INSERT INTO Membership (type, customer, invoice, expireDate, cost) VALUES  \n")
    cnt = 1
    for i in membership_data:
        file.write(str(i))
        if cnt < len(membership_data):
            file.write(",\n")
        else:
            file.write(";\n")
        cnt +=1
    file.write("\n\n")

    file.write("INSERT INTO TrainersLicense (trainer, license) VALUES   \n")
    cnt = 1
    for i in trainers_license_data:
        file.write(str(i))
        if cnt < len(trainers_license_data):
            file.write(",\n")
        else:
            file.write(";\n")
        cnt +=1
    file.write("\n\n")

    # file.write("INSERT INTO WorkoutClass (name, day, time, teacher, capacity) VALUES  \n")
    file.write("INSERT INTO WorkoutClass (id, name, day, time, teacher, capacity) VALUES  \n")
    cnt = 1
    for i in workout_classes_data:
        file.write(str(i))
        if cnt < len(workout_classes_data):
            file.write(",\n")
        else:
            file.write(";\n")
        cnt +=1
    file.write("\n\n")

    # file.write("INSERT INTO TakeClass (id, customer, name, day, time, teacher, price) VALUES  \n")
    file.write("INSERT INTO TakeClass (id, customer, price) VALUES  \n")
    cnt = 1
    for i in take_classes_data:
        file.write(str(i))
        if cnt < len(take_classes_data):
            file.write(",\n")
        else:
            file.write(";\n")
        cnt +=1
    file.write("\n\n")

file.close()