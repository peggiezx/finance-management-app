import React, {useEffect, useState} from "react";
import ExpenseService from "./ExpenseService";
import { Link } from 'react-router-dom';

const ExpesenList = () => {
    const [expenses, setexpense] = useState([]);

    useEffect(() => {
        fetchExpenses();
    }, []);
}