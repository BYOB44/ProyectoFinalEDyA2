import { useCallback, useEffect, useState } from 'react';
import { apiRequest } from '../helpers/api.js';

export function useFetch(path, immediate = true) {
    const [data, setData] = useState(null);
    const [loading, setLoading] = useState(immediate);
    const [error, setError] = useState('');

    const load = useCallback(async () => {
        try {
            setLoading(true);
            setError('');
            const response = await apiRequest(path);
            setData(response);
            return response;
        } catch (err) {
            setError(err.message);
            return null;
        } finally {
            setLoading(false);
        }
    }, [path]);

    useEffect(() => {
        if (immediate) {
            load();
        }
    }, [immediate, load]);

    return { data, setData, loading, error, load };
}
