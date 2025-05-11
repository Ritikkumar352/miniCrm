// main.js
document.addEventListener('DOMContentLoaded', function() {
    // Navigation
    document.querySelectorAll('.nav-links a').forEach(link => {
        link.addEventListener('click', function(e) {
            e.preventDefault();
            const page = this.dataset.page;
            showPage(page);
        });
    });

    // Login button
    document.getElementById('loginBtn').addEventListener('click', function() {
        window.location.href = '/oauth2/authorization/google';
    });

    // Check authentication status
    checkAuthStatus();
});

function showPage(page) {
    // Hide all sections
    document.querySelectorAll('.content-section').forEach(section => {
        section.classList.add('hidden');
    });
    
    // Show selected section
    document.getElementById(page).classList.remove('hidden');
    
    // Update active nav link
    document.querySelectorAll('.nav-links a').forEach(link => {
        link.classList.remove('active');
        if (link.dataset.page === page) {
            link.classList.add('active');
        }
    });

    // Load data for the page
    loadPageData(page);
}

function loadPageData(page) {
    switch(page) {
        case 'customers':
            loadCustomers();
            break;
        case 'segments':
            loadSegments();
            break;
        case 'campaigns':
            loadCampaigns();
            break;
    }
}

function loadCustomers() {
    fetch('/api/v1/customer')
        .then(response => response.json())
        .then(data => {
            const customerList = document.getElementById('customerList');
            customerList.innerHTML = data.content.map(customer => `
                <div class="card">
                    <h3>${customer.name}</h3>
                    <p>Email: ${customer.email}</p>
                    <p>Phone: ${customer.phone}</p>
                </div>
            `).join('');
        })
        .catch(error => console.error('Error loading customers:', error));
}

function loadSegments() {
    fetch('/api/v1/segments')
        .then(response => response.json())
        .then(data => {
            const segmentList = document.getElementById('segmentList');
            segmentList.innerHTML = data.map(segment => `
                <div class="card">
                    <h3>${segment.name}</h3>
                    <p>${segment.description}</p>
                </div>
            `).join('');
        })
        .catch(error => console.error('Error loading segments:', error));
}

function loadCampaigns() {
    fetch('/api/v1/campaigns')
        .then(response => response.json())
        .then(data => {
            const campaignList = document.getElementById('campaignList');
            campaignList.innerHTML = data.map(campaign => `
                <div class="card">
                    <h3>${campaign.name}</h3>
                    <p>Segment: ${campaign.segmentName}</p>
                </div>
            `).join('');
        })
        .catch(error => console.error('Error loading campaigns:', error));
}

function checkAuthStatus() {
    fetch('/api/v1/auth/current-user')
        .then(response => {
            if (response.ok) {
                return response.json();
            }
            throw new Error('Not authenticated');
        })
        .then(user => {
            document.getElementById('userName').textContent = user.name;
            document.getElementById('loginBtn').style.display = 'none';
        })
        .catch(() => {
            document.getElementById('userName').textContent = 'Not logged in';
            document.getElementById('loginBtn').style.display = 'block';
        });
}