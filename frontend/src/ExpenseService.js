import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api/expenses';

const getAllExpense = async () => {
    try {
        const response = await axios.get(API_BASE_URL);
        return response.data;
    } catch (error) {
        console.error('Error fetching expenses: ', error);
        throw error;
    }
};

const getExpenseById = async (id) => {
    try {
        const response = await axios.get(`${API_BASE_URL}/${id}`);
        return response.data;
    } catch (error) {
        console.error(`Error fetching expense for ID ${id}: `, error);
        throw error;
    }
};

const createExpense = async (expense) => {
    try {
        const response = await axios.post(API_BASE_URL, expense);
        return response.data;
    } catch (error) {
        console.error(`Error creating expense for ${expense}: `, error);
        throw error;
    }
};

const updateExpense = async (id, expense) => {
    try {
        const response = await axios.put(`${API_BASE_URL}/${id}`, expense);
        return response.data;
    } catch (error) {
        console.error(`Error updating expense with id ${id}: `, error);
        throw error;
    }
};

const deleteExpense = async (id) => {
    try {
        const response = await axios.delete(`${API_BASE_URL}/${id}`);
        return response.data;
    } catch (error) {
        console.error(`Error deleting expense with id ${id}: `, error);
        throw error;
    }
};

export default {
    getAllExpense,
    getExpenseById,
    createExpense,
    updateExpense,
    deleteExpense,
};